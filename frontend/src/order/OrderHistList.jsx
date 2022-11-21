import React, { useState, useEffect } from 'react';
import axios from 'axios'
import OrderHist from "./OrderHist";
import { alertTitleClasses } from '@mui/material';

function OrderHistList() {
    const [ testStr, setTestStr ] = useState('');
    const[pageNum, setPageNum] = useState(0);
    const [orderCount, setOrderCount] = useState(0);
    const page = []
    const [page2, setPage2] = useState(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // 변수 초기화
    function callback(str) {
      setTestStr(str);
    }

    useEffect(
        () => {
          axios({
              url: '/orders',
              method: 'GET',
              headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              },
              params: {
                "pageNum": pageNum
              }
          }).then((res) => {
              callback(res.data);
              setOrderCount(res.data.orderCount);
            
              if(res.data.problem != false) {
                alert("구매이력을 불러오던 중 문제가 발생하였습니다.")
              }
          }).catch((error) => {
                if(error.response.status === 403 || error.response.status === 400) {
                    alert("권한이 없습니다.")
                    window.location.href = "/members/login"
                }
            })
        }, []


    );

    // 페이징

    let arr = []
    const arrLength = 0
    for(let i = 0 ; i < Math.ceil(orderCount/5) ; i++) {
        arr.push(i+1)
    }

    

    for(let i = 1 ; i < 11 ; i++) {
        if(arr.length < i) {
            break;
        }else {
            page.push(i);
        }
    }
   

    const index = []
    for(let i = 0 ; i < Math.ceil(arr.length/10) ; i ++) {
        index.push(i)
    }        


    const prevPage = (e) => {
        e.preventDefault();
        if (pageNum === 0) {
            alert("첫번째 페이지 입니다.")
        } else {
            setPageNum(pageNum - 1);
        axios({
            url: '/orders',
            method: 'GET',
            headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              },
            params: {
                "pageNum": pageNum-1
            }
        }).then((res) => {
            callback(res.data);
        })
        }
    }

    const nextPage = (pageNum, e) => {
        e.preventDefault();
        for(let i = 0 ; i < 10 ; i++) {
            page.pop()
        }
        
        for(let k = 0 ; k < index.length;k++) {
            if (Math.ceil((pageNum+2)/10) == index[k]) {
                
                for(let j = 1 ; j < 11 ; j++) {
                    if(arr.length < k) {
                        break;
                    }else {
                        page.push(parseInt(j + ((index[k]-1)*10)))
                        setPage2(page);
                    }
                }
            }
        }

        


        if(pageNum >= Math.ceil(orderCount/5) - 1) {
            alert("마지막 페이지입니다.")
        } else {
            setPageNum(pageNum + 1);
            axios({
                url: '/orders',
                method: 'GET',
                headers: { 
                    'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                    'Content-Type': 'application/json' 
                  },
                params: {
                    "pageNum": pageNum+1
                }
            }).then((res) => {
                callback(res.data);
            })
        }
    }

    const clickPage = (page, e) => {
        e.preventDefault();
        setPageNum(page)
        axios({
            url: '/orders',
            method: 'GET',
            headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              },
            params: {
                "pageNum": page
            }
        }).then((res) => {
            callback(res.data);
        })
    }
    
    // 주문취소
    const cancelOrder = (id) => {

        const paramData = {
            orderId: id
        }

        const param = JSON.stringify(paramData);

        axios({
            url: '/order/'+id+"/cancel",
            method: 'POST',
            headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              },
            data: param
        }).then((res) => {
            callback(res.data);
            if(res.data.problem != false) {
                alert("주문 취소 중 문제가 발생하였습니다.");
            }
            window.location.href = "/order/orderHist";
        })
    }

    console.log(page)
    console.log("2 ", page2)



    return(
        <>
            {testStr.orderItem && testStr.orderItem.map((orderItem) => {
                <li key={orderItem.id} />

                    return(
                        <div className="orderHistListForm">
                                <div class="d-flex mb-3 align-self-center">
                                <h4 >{orderItem.orderDate}</h4>
                                <div class="ml-3">

                                {
                                orderItem.orderStatus == "ORDER"
                                ? <button type="button" class="btn btn-outline-secondary"  
                                onClick={()=>cancelOrder(orderItem.id)}> 주문취소</button>
                                : <h4>(취소 완료)</h4>
                                }

                                </div>
                        </div>
    
                        
                            <div class="cardOrderHist">
                                <div>
                                    {orderItem.itemDtoList && orderItem.itemDtoList.map((items) => {
                                        return (
                                            <div className='d-flex mb-3'>
                                            <div class="repImgDiv">
                                                <img src={items.imgUrl} class = "imgOrderHist" />
                                            </div>
        
                                            <div class="contentsOrderHist">
                                                <span  class="fs24 font-weight-bold">{items.itemNm}</span>
                                                <div class="fs18 font-weight-light">
                                                    <span>{items.count}</span>
                                                </div>
                                                <div class="fs18 font-weight-light">
                                                    <span>판매자 </span>
                                                    <span >{items.itemSeller}</span>
                                                </div>
                                                <div class="fs18 font-weight-light">
                                                    <span>배송번호: </span>
                                                    <span >{orderItem.logistic}</span>
                                                </div>
                                            </div>
                                            </div>
                                        )
                                    })}
                                    
                                </div>
                            </div>

                            
                        </div>
                    )
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
            {page.map((page) => {
                return(
                    <>
                <li className='page-item'>
                        <a className='page-link'
                        href={'?page=' + (page-1)} 
                        onClick={(e) => clickPage(page-1, e)}
                        >{page}
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
    )
}

export default OrderHistList;