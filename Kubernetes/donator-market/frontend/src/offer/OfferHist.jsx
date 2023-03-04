import React, { Component } from 'react';
import './OfferHist.css';
import OfferHistList from './OfferHistList';

function OfferHist(props) {
        return(
            <div class="containerOfferHist">
                
                
            <h2 class="mb-4">판매 이력</h2>
            <div>

                <OfferHistList/>

            </div>
            </div>
        );
}

export default OfferHist;