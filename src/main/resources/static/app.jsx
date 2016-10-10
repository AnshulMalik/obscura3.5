define(function (require) {
	'use strict';

	var React = require('react');
	var ReactDOM = require('reactDOM');
	var client = require('./client');
	var follow = require('./follow');
	var root = '/api';
	var stompClient = require('./websocket-listener');

	var App = React.createClass({
		loadFromServer: function (pageSize) {
			follow(client, root, [
				{rel: 'obUsers', params: {size: pageSize}}]
			).then(userCollection => {
				return client({
					method: 'GET',
					path: userCollection.entity._links.profile.href,
					headers: {'Accept': 'application/schema+json'}
				}).then(schema => {
					this.schema = schema.entity;
					return userCollection;
				});
			}).done(userCollection => {
				this.setState({
					users: userCollection.entity._embedded.obUsers,
					attributes: Object.keys(this.schema.properties),
					pageSize: pageSize,
					links: userCollection.entity._links});
			});
		},

		onCreate: function (newUser) {
			follow(client, root, ['obUsers']).then(userCollection => {
				return client({
					method: 'POST',
					path: userCollection.entity._links.self.href,
					entity: newUser,
					headers: {'Content-Type': 'application/json'}
				})
			}).then(response => {
				return follow(client, root, [
					{rel: 'obUsers', params: {'size': this.state.pageSize}}]);
			});
		},
		getInitialState: function () {
			return ({users: [], attributes: [], pageSize: 2, links: {}});
		},
		componentDidMount: function () {
			this.loadFromServer(this.state.pageSize);
			stompClient.register([
				{route: '/update/newUser', callback: this.newUserAdded}
			]);
		},
		newUserAdded: function(message) {
			console.log("New user added");
			console.log(message);
		},
		render: function () {
			return (
				<div>
					<GoogleLogin
						clientId={'679139204576-u8o5pmk50lqbdhk75kf67t8uoa44f2ne.apps.googleusercontent.com'}
					    callback={responseGoogle}
					    offline={false}
					>
						<span> Login with Google</span>
					</GoogleLogin>
					<CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
					<UserList users={this.state.users}
								  links={this.state.links}
								  pageSize={this.state.pageSize}
								  onNavigate={this.onNavigate}
								  onDelete={this.onDelete}
								  updatePageSize={this.updatePageSize}/>
				</div>
			)
		}
	})


	var CreateDialog = React.createClass({

		handleSubmit: function (e) {
			e.preventDefault();
			var newUser = {};
			this.props.attributes.forEach(attribute => {
				newUser[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
			});
			this.props.onCreate(newUser);

			// clear out the dialog's inputs
			this.props.attributes.forEach(attribute => {
				ReactDOM.findDOMNode(this.refs[attribute]).value = '';
			});

			// Navigate away from the dialog to hide it.
			window.location = "#";
		},

		render: function () {
			var inputs = this.props.attributes.map(attribute =>
				<p key={attribute}>
					<input type="text" placeholder={attribute} ref={attribute} className="field" />
				</p>
			);

			return (
				<div>
					<a href="#createUser">Create</a>

					<div id="createUser" className="modalDialog">
						<div>
							<a href="#" title="Close" className="close">X</a>

							<h2>Create new User</h2>

							<form>
								{inputs}
								<button onClick={this.handleSubmit}>Create</button>
							</form>
						</div>
					</div>
				</div>
			)
		}

	});
	// end::create-dialog[]

	var UserList = React.createClass({
		// tag::handle-page-size-updates[]
		handleInput: function (e) {
			e.preventDefault();
			var pageSize = ReactDOM.findDOMNode(this.refs.pageSize).value;
			if (/^[0-9]+$/.test(pageSize)) {
				this.props.updatePageSize(pageSize);
			} else {
				ReactDOM.findDOMNode(this.refs.pageSize).value =
					pageSize.substring(0, pageSize.length - 1);
			}
		},
		// end::handle-page-size-updates[]
		// tag::handle-nav[]
		handleNavFirst: function(e){
			e.preventDefault();
			this.props.onNavigate(this.props.links.first.href);
		},
		handleNavPrev: function(e) {
			e.preventDefault();
			this.props.onNavigate(this.props.links.prev.href);
		},
		handleNavNext: function(e) {
			e.preventDefault();
			this.props.onNavigate(this.props.links.next.href);
		},
		handleNavLast: function(e) {
			e.preventDefault();
			this.props.onNavigate(this.props.links.last.href);
		},
		// end::handle-nav[]
		// tag::employee-list-render[]
		render: function () {
			var users = this.props.users.map(user =>
				<User key={user._links.self.href} user={user} onDelete={this.props.onDelete}/>
			);

			var navLinks = [];
			if ("first" in this.props.links) {
				navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
			}
			if ("prev" in this.props.links) {
				navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
			}
			if ("next" in this.props.links) {
				navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
			}
			if ("last" in this.props.links) {
				navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
			}

			return (
				<div>
					<input ref="pageSize" defaultValue={this.props.pageSize} onInput={this.handleInput}/>
					<table>
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
							</tr>
						</thead>
						<tbody>
							{users}
						</tbody>
					</table>
					<div>
						{navLinks}
					</div>
				</div>
			)
		}
		// end::employee-list-render[]
	})

	// tag::employee[]
	var User = React.createClass({
		handleDelete: function () {
			this.props.onDelete(this.props.user);
		},
		render: function () {
			return (
				<tr>
					<td>{this.props.user.firstName}</td>
					<td>{this.props.user.lastName}</td>
					<td>
						<button onClick={this.handleDelete}>Delete</button>
					</td>
				</tr>
			)
		}
	})
	// end::employee[]

	ReactDOM.render(
		<App />,
		document.getElementById('react')
	)

});
