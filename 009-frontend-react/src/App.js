import React from 'react';

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: null
    };
  }

  handleOnBlur = (event) => {
    const value = event.target.value;
    this.setState({
      name: value
    });
  }

  render() {
    return (
      <div align="center">
        <p>
          Enter your name:
        </p>
        <input type="text" onBlur={this.handleOnBlur} />
        <p>
          { this.state.name ? `Hello, ${this.state.name}`: null }
        </p>
      </div>
    );
  }
}

export default App;
