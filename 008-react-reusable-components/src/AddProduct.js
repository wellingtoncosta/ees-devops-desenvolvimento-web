import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { products } from './products.js';

function AddProduct() {
    const navigate = useNavigate()
    const [name, setName] = useState('')
    const [price, setPrice] = useState('')
    const [category, setCategory] = useState('')
    
    return (
        <div>
            <label>
                Name:
                {' '}
                <input value={name} onChange={(e) => setName(e.target.value)} />
            </label>
            <br/>
            <br/>
            <label>
                Price:
                {' '}
                <input value={price} onChange={(e) => setPrice(e.target.value)} />
            </label>
            <br/>
            <br/>
            <label>
                Category:
                {' '}
                <input value={category} onChange={(e) => setCategory(e.target.value)} />
            </label>
            <br/>
            <br/>
            <br/>
            <br/>
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

export default AddProduct;
