import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from './fragment/Header';
import React, { useState, useEffect } from 'react';
import Footer from './fragment/Footer';
function App() {

  console.log(process.env.REACT_APP_ENV_KEY)

  return (
    <>
    <Header></Header>
    <Footer></Footer>
    </>
  );
}

export default App;
