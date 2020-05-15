import React from 'react';

class Input extends React.Component {
  render() {
    return (
      <input type="text" />
    );
  }
}

const inputWithLabel = function(Input) {  
  return (
    <div>
      <p>
        Type something:
      </p>
      <Input />
    </div>
  )
}

export default function() {
  return inputWithLabel(Input);
};
