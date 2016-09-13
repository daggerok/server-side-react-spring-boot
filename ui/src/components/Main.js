/**
 * Created by mak on 9/6/16.
 */
import React from 'react';
import Nav from "./Nav";

export default ({posts}) => (
  <div>
    <span>main component</span>
    <Nav/>
    <ul>
      {posts ? posts.map((post, i) => <li key={i}>{post.id}) {post.text}</li>) : <li>empty</li>}
    </ul>
  </div>
);
