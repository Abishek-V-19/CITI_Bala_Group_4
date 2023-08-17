import "./App.css";
import AppRouter from "./AppRouter";
import NavBar from './components/NavBar'
import Footer from './components/Footer'

function App() {
  return (
    <div className="App">
      <NavBar />
      <AppRouter />
      {/* <Footer/> */}
    </div>);
}

export default App;
