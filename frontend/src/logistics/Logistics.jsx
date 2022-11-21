import React, { useState, useEffect } from 'react';
import axios from 'axios'
import './Logistics.css';

function Logistics() {

    const [ testStr, setTestStr ] = useState('');

    // 변수 초기화
    function callback(str) {
    setTestStr(str);
    }

    const[search, setSearch] = useState('')
    const handleSearchChange = (event) => {
        setSearch(event.target.value);
    }

    const handleSearchClick = (e) => {
        e.preventDefault();
        axios({
            url: '/logistics/search',
            method: 'GET',
            headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
            },
            params: {
                "searchName": search
            }
        }).then((res) => {
            callback(res.data);
        })
    }


        return(
            <div className="containerItemMng">

            <input type="text" placeholder="운송번호를 입력해주세요"
                value={search} onChange={handleSearchChange}/>
            <button id="searchBtn" type="submit" className="btn btn-primary"
                onClick={handleSearchClick}>
                    배송조회
            </button>


            <form role="form" method="get" className='itemMngForm'>
                <table className="table">
                    <thead>
                    <tr>
                        <td>운송번호</td>
                        <td>배송시간</td>
                        <td>배송상태</td>
                    </tr>
                    </thead>

                    <tbody> 
                        {testStr.logistic && testStr.logistic.map((logistic) => {
                            return(
                                <tr>
                                    <td>{logistic.logistic}</td>
                                    <td>{logistic.date}</td>
                                    <td>{logistic.status}</td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>

            </form>

        

            </div>
        );
}

export default Logistics;   