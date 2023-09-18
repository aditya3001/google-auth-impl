import logo from './logo.svg';
import './App.css';
import Navbar from './components/navbar/Navbar';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './components/home/Home';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <header className="App-header">
          <Navbar />
        </header>
        <Routes>
          <Route path='/home' exact element={<Home />}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
