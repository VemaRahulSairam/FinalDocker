import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Routes,Route } from 'react-router-dom';
import Login from './Pages/Login'
import UserList from './Pages/UserList';

function App() {
  return (
    <div className="App" style={{ 
      backgroundSize:'cover',
      width: '100vw',
      height: '100vh',
      backgroundPosition:'center'
       }}>

   
      <BrowserRouter>
      <div>
      <Routes>
      <Route path="/" element={<Login/>} /> 
      <Route exact path="/dashboard" element={<UserList/>} />
      </Routes>
      </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
