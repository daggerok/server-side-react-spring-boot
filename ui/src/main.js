/**
 * Created by mak on 9/6/16.
 */
import './assets';

import React, { Component } from "react";
import { render } from "react-dom";
import { Router, Route, IndexRoute, browserHistory, Link } from 'react-router';

import NotFound from "./components/NotFound";
import Main from "./components/Main";

class App extends Component {
  constructor() {
    super();
    this.ONE_SECOND = 1000;
    this.state = {seconds: 0};
    this.incrementTimerAndUpdateState = this.incrementTimerAndUpdateState.bind(this);
  }

  componentDidMount() {
    this.timer = setInterval(this.incrementTimerAndUpdateState, this.ONE_SECOND)
  }

  incrementTimerAndUpdateState() {
    this.setState({ seconds: 1 + this.state.seconds });
  }

  componentWillUnmount() {
    clearInterval(this.timer);
  }

  render() {
    return (
      <div>
        <div>I am living {this.state.seconds} seconds!</div>
        {this.props.children}
      </div>
    );
  }
}

render(
  <Router history={browserHistory}>
    <Route path="/" component={App}>
      <IndexRoute component={Main}/>
      <Route path="home" component={Main}/>
      <Route path="*" component={NotFound}/>
    </Route>
  </Router>,
  document.getElementById('app')
);
