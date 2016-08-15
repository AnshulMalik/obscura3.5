(function () {

	requirejs.config({
		paths: {
			"react": "bower_components/react/react",
			"reactDOM": "bower_components/react/react-dom",
			"es6": "node_modules/requirejs-babel/es6",
			"jsx" : "bower_components/requirejs-react-jsx/jsx",
			"babel": "node_modules/requirejs-babel/babel-5.8.34.min",
			"sockjs-client": "bower_components/sockjs-client/dist/sockjs",
			"stomp-websocket": "bower_components/stomp-websocket/lib/stomp.min",
		},
		packages: [
		    {name: "rest", location: "bower_components/rest" , main: "browser"},
			{name: "when", location: "bower_components/when", main: "when"},
			{name: "text", location : "bower_components/requirejs-text", main: "text"} 			
		],
		shim: {
			"react": {
				"exports": "React"
			},
			"reactDOM": {
				"exports": "ReactDOM"
			}
			
		},
		deps: ['main'],
		config: {
			babel: {
				sourceMaps: "inline",
				fileExtension: ".jsx"
			}
		}
	});

}());