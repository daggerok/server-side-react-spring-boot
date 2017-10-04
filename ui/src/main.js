/**
 * Created by mak on 9/6/16.
 */
import './assets';

import React, { Component } from "react";
import { render } from "react-dom";
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

global.renderClient = function(json) {
  const posts = json || [];
  // console.log('client posts', JSON.stringify(posts));
  return render(
    <App><Main posts={posts}/></App>,
    document.getElementById('app')
  );
};

import { renderToString } from 'react-dom/server'
global.renderServer = function(java) {
  const posts = Java.from(java || []);
  // console.log('server posts', posts);
  return renderToString(<App><Main posts={posts}/></App>);
};
