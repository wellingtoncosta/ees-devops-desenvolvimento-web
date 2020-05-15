import React from 'react';
import InputWithLabel from './Input';
import ClickMe from './Hooks';
import ListUsers from './ListUsers';

function App() {
  return (
    <div align="center">
      <h1>Hello!</h1>
      <br />
      <InputWithLabel />
      <br />
      <br />
      <ClickMe />
      <br />
      <br />
      <ListUsers />
    </div>
  );
}

export default App;
