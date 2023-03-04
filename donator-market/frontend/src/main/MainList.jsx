import React, { useState, useEffect } from 'react';
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css';  
import {Container ,Card,Row, Col, Button} from 'react-bootstrap';  
import './Main.css';
import Pagination from 'react-js-pagination';

function MainList() {


    const [ testStr, setTestStr ] = useState('');
    const[pageNum, setPageNum] = useState(0);
    const [itemCount, setItemCount] = useState(0);

    // 변수 초기화
    function callback(str) {
    setTestStr(str);
    }

    
    useEffect(
        () => {
         axios({
            url: '/main',
            method: 'GET',
            params: {
                "pageNum": pageNum
            }
        }).then((res) => {
            callback(res.data);
            setItemCount(res.data.itemCount);
            console.log(res.data)
            if(res.data.problem != false) {
                alert("화면을 불러오는데 문제가 발생하였습니다.");
            }
        })
        }, []
); 

let arr = []
for(let i = 0 ; i < Math.ceil(itemCount/9) ; i++) {
    arr.push(i+1)
}

        const prevPage = (e) => {
            e.preventDefault();
            if (pageNum === 0) {
                alert("첫번째 페이지 입니다.")
            } else {
                setPageNum(pageNum - 1);
                axios({
                    url: '/main',
                    method: 'GET',
                    params: {
                        "pageNum": pageNum-1
                    }
                }).then((res) => {
                    callback(res.data);
                    console.log("prevPage ", res.data.itemsObj[0].pageNum);
                })
            }
        }
    
        const nextPage = (pageNum, e) => {
            e.preventDefault();
            if(pageNum >= Math.ceil(itemCount/9) - 1) {
                alert("마지막 페이지입니다.")
            } else {
                setPageNum(pageNum + 1);
                console.log("after ", pageNum)
                axios({
                    url: '/main',
                    method: 'GET',
                    params: {
                        "pageNum": pageNum+1
                    }
                }).then((res) => {
                    callback(res.data);
                    console.log("nextPage ", res.data.itemsObj[0].pageNum);
                })
            }
        }

        const clickPage = (arr, e) => {
            e.preventDefault();
            setPageNum(arr)
            console.log(arr)
            axios({
                url: '/main',
                method: 'GET',
                params: {
                    "pageNum": arr
                }
            }).then((res) => {
                callback(res.data);
                console.log("nextPage ", res.data.itemsObj[0].pageNum);
            })
        }
   
    return(
        <>
            {testStr.items && testStr.items.map((item) => {
                return(
                    <>
                    
                        <Card className="card" key={item.id}>
                            <a href={item.id}>
                                <img className="card-img-top" src={"https://shopitemimage.s3.ap-northeast-2.amazonaws.com/itemImg/"+item.oriImgName} />
                                <div className="card-body">
                                    <h4 className="card-title">{item.name}</h4>
                                    <p className="card-text">{item.dtl}</p>
                                    <h3 class="card-title text-danger">{item.stockNumber}</h3>
                                </div>
                            </a>
                        </Card>
                    </>
                );
            })}
                    <>

                    <div>
                        <ul className='pagination justify-content-center'>

                        <li className='page-item'>
                            <a className='page-link'  disabled={pageNum==0?true:false} 
                            href={'?page='+ (pageNum)} type='button'
                            onClick={prevPage}
                            >Previous
                            </a>
                        </li>
                        {arr.map((arr) => {
                            return(
                                <>
                            <li className='page-item'>
                                    <a className='page-link'
                                    href={'?page=' + (arr-1)} 
                                    onClick={(e) => clickPage(arr-1, e)}
                                    >{arr} 
                                    </a>
                                </li>
                                </>
                            )
                        })}

                        <li className='page-item'>
                            <a className="page-link"  disabled={(pageNum+1)==pageNum?true:false } 
                            href={'?page=' + (pageNum)} type='button'
                            onClick={(e)=>nextPage(pageNum, e)} 
                            >Next
                            </a>
                        </li>
                    </ul>
                    </div>
                    </>             

           

        </>
    );
}

export default MainList;