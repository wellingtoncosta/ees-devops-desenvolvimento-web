import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { products } from './products.js';

function Form({ name, onNameChanged, price, onPriceChanged, category, onCategoryChanged }) {
    return (
        <div>
            <label>
                Name:
                {' '}
                <input value={name} onChange={(e) => onNameChanged(e.target.value)} />
            </label>
            <br/>
            <br/>
            <label>
                Price:
                {' '}
                <input value={price} onChange={(e) => onPriceChanged(e.target.value)} />
            </label>
            <br/>
            <br/>
            <label>
                Category:
                {' '}
                <input value={category} onChange={(e) => onCategoryChanged(e.target.value)} />
            </label>
        </div>
    )
}

function Buttons({name, price, category, navigate}) {
    return (
        <div>
            <button onClick={(e) => {
                products.push({
                    name: name,
                    price: price,
                    category: category
                })

                navigate('/')
            }}>Save</button>
            {' '}
            <button onClick={(e) => navigate('/')}>Back</button>
        </div>
    )
}

function AddProduct() {
    const navigate = useNavigate()
    const [name, setName] = useState('')
    const [price, setPrice] = useState('')
    const [category, setCategory] = useState('')

    return (
        <div>
            <Form
                name={name}
                onNameChanged={name => setName(name)}
                price={price}
                onPriceChanged={price => setPrice(price)}
                category={category}
                onCategoryChanged={category => setCategory(category)}
            />

            <br/>
            <br/>
            <br/>
            <br/>

            <Buttons
                name={name}
                price={price}
                category={category}
                navigate={navigate}
            />
        </div>
    )
}

export default AddProduct;
