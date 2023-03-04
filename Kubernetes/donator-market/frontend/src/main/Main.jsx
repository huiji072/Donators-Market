import React, { Component, useState, useEffect } from 'react';
import './Main.css';
import axios from 'axios'
import MainList from './MainList';
import 'bootstrap/dist/css/bootstrap.min.css';  
import {Container ,Card,Row, Col, Button} from 'react-bootstrap';  

function Main(props) {

    // const clickToNaver = () => {
    //     axios({
    //         url: '/naver',
    //         method: 'GET',
    //         headers: { 
    //             'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
    //             'Content-Type': 'application/json' 
    //           },
    //     }).then((res) => {
    //         alert("success")
    //     })
    
    // }

    return (
        <div>
            <Container className="mainContainer">
            {/* <Button onClick={clickToNaver}>
                네이버로 이동</Button>  */}
                <Row className='mainRow'>
                    <MainList/>
                </Row>
            </Container>
        </div>
            
        );
}


export default Main;