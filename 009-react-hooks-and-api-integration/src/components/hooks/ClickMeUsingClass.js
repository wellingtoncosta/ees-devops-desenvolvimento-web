import React from 'react';

class ClickMeUsingClass extends React.Component {
    constructor() {
        super();
        this.state = {count: 0};
    }

    handleOnClick() {
        const {count} = this.state;
        this.setState({count: count + 1});
    }

    render() {
        const {count} = this.state;
        return (
            <div>
                <p>Button was clicked {count} times.</p>
                <button onClick={this.handleOnClick.bind(this)}>Click me!</button>
            </div>
        )
    }
}

export default ClickMeUsingClass;
