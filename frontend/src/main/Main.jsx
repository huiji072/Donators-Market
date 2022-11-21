import React, { Component, useState, useEffect } from 'react';
import './Main.css';
import MainList from './MainList';
import 'bootstrap/dist/css/bootstrap.min.css';  
import {Container ,Card,Row, Col, Button} from 'react-bootstrap';  

function Main(props) {


    return (
        <div>
            <Container className="mainContainer">
                <Row className='mainRow'>
                    <MainList/>
                </Row>
            </Container>
        </div>
            
        );
}


export default Main;