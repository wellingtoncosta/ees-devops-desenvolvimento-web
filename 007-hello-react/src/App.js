import { useState } from 'react';

import './App.css';

function Greetings({name}){
    return (
        <p>{name.trim() !== '' ? `Hello, ${name}!` : null}</p>
    ) 
} 

function App() {
    const [name, setName] = useState('')

    const onNameChanged = (event) => setName(event.target.value)

    return (
        <div className="App">
            <p>What's your name?</p>
            <input onChange={onNameChanged}></input>
            <Greetings name={name}/>
        </div>
    );
}

export default App;
