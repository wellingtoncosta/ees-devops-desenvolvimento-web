import React, { useState } from 'react';

// class ClickMe extends React.Component {

//   constructor() {
//     super();
//     this.state = { count: 0 };
//   }

//   handleOnClick = () => {
//     const { count } = this.state; // const count = this.staate.count;
//     this.setState({ count: count + 1 });
//   }

//   render() {
//     const { count } = this.state; // const count = this.staate.count;
//     return (
//       <div>
//         <p>Button was clicked {count} times.</p>
//         <button onClick={this.handleOnClick}>Click me!</button>
//       </div>
//     )
//   }

// }

function ClickMeWithHooks() {
  const [count, setCount] = useState(0);
  return (
    <div>
      <p>Button was clicked {count} times.</p>
      <button onClick={() => setCount(count + 1)}>Click me!</button>
    </div>
  );
}

export default ClickMeWithHooks; // export default ClickMe;
