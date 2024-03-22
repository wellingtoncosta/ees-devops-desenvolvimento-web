import { useState } from "react";
import { useNavigate } from "react-router-dom";
import './App.css'

function SearchBar({ filterText, onFilterTextChanged, fruitsOnly, onFruitsOnlyChecked }) {
  return (
    <form className="Search">
      <label>
        Name:
        {' '}
        <input
          placeholder="Search..."
          value={filterText}
          onChange={(e) => onFilterTextChanged(e.target.value)}
        />
      </label>

      <label>
        <input
          type="checkbox"
          checked={fruitsOnly}
          onChange={(e) => onFruitsOnlyChecked(e.target.checked)}
        />
        {' '}
        Fruits only
      </label>
    </form>
  );
}

function ProductTable({ products, fruitsOnly, filterText }) {
  const rows = products
    .filter(product => !(product.category === "Vegetables" && fruitsOnly))
    .filter(product => product.name.toLowerCase().indexOf(filterText.toLowerCase()) !== -1)
    .map(product => <ProductRow product={product} />)
  
  return (
    <div className="ProductTable">
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Category</th>
          </tr>
        </thead>
        <tbody>
          {rows}
        </tbody>
      </table>
    </div>
  )
}

function Footer({ navigate }) {
  return (
    <div className="AddProduct">
      <button onClick={(e) => navigate('/add')}>Add</button>
    </div>
  );
}

function ProductRow({ product }) {
  return (
    <tr>
      <td>{product.name}</td>
      <td>{product.price}</td>
      <td>{product.category}</td>
    </tr>
  )
}

function App({ products }) {
  const navigate = useNavigate();
  const [filterText, setFilterText] = useState('')
  const [fruitsOnly, setFruitsOnly] = useState(false)
  
  return (
    <div className="App">
      <SearchBar
        filterText={filterText}
        onFilterTextChanged={value => setFilterText(value)}
        fruitsOnly={fruitsOnly}
        onFruitsOnlyChecked={value => setFruitsOnly(value)}
      />
      
      <ProductTable products={products} filterText={filterText} fruitsOnly={fruitsOnly} />
      
      <Footer navigate={navigate} />
    </div>
  );
}

export default App;
