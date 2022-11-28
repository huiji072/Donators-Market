import React from "react";
import './Footer.css';

function Footer() {

    return(
        <>
        <div className="footerWrap">
            <div className="footer">
                <ul className="footerTitleList">
                    <li>문의</li>
                    <li>이메일</li>
                    <li>기부계좌</li>
                </ul>
                <ul className="footerTextList">
                    <li>010-2066-**** | 
                    <br className="brM"/>
                    &nbsp; 070-****-****
                    </li>
                    <li>gmlwl0720@naver.com</li>
                    <li>신한은행 110-***-****** 
                        <br className="brM"/>
                        &nbsp;(예금주:김희지)
                    </li>
                </ul>
            </div>

        <div  className='text-white'>
            ⓒ Donator's Market
        </div>


        </div>            
        </>
    )

}

export default Footer;