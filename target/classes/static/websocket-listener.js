define(function(require) {
	'use strict';

	var SockJS = require('sockjs-client');
	require('stomp-websocket');

	return {
		register: register
	};

	
	function register(registrations) {
		var socket = SockJS('/users');
		var stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			registrations.forEach(function (registration) {
			  stompClient.subscribe(registration.route, registration.callback);
			});
		});
	}

});
