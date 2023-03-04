import React, { Component } from 'react';
import './OrderHist.css';
import OrderHistList from './OrderHistList';

function OrderHist(props) {
        return(
            <div class="containerOrderHist">
                
                
            <h2 class="mb-4">구매 이력</h2>
            <div>
                <OrderHistList/>
            </div>

            </div>
        );
}

export default OrderHist;