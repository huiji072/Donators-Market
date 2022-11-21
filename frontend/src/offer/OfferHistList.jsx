import React, { useState, useEffect } from 'react';
import axios from 'axios'
import "./OfferHist.css";


function OfferHistList() {

    const [ testStr, setTestStr ] = useState('');
    const[pageNum, setPageNum] = useState(0);
    const [offerCount, setofferCount] = useState(0);

    // 변수 초기화
    function callback(str) {
      setTestStr(str);
    }

    useEffect(
        () => {
          axios({
              url: '/offers',
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
              setofferCount(res.data.offerCount);
              console.log(res.data)
              if(res.data.problem != false) {
                alert("판매이력을 불러오던 중 문제가 발생하였습니다.")
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
    for(let i = 0 ; i < Math.ceil(offerCount/5) ; i++) {
        arr.push(i+1)
    }
    
    const prevPage = (e) => {
        e.preventDefault();
        if (pageNum === 0) {
            alert("첫번째 페이지 입니다.")
        } else {
            setPageNum(pageNum - 1);
            axios({
                url: '/offers',
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
        if(pageNum >= Math.ceil(offerCount/5) - 1) {
            alert("마지막 페이지입니다.")
        } else {
            setPageNum(pageNum + 1);
            console.log("after ", pageNum)
            axios({
                url: '/offers',
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

    const clickPage = (arr, e) => {
        e.preventDefault();
        console.log(arr)
        setPageNum(arr)
        axios({
            url: '/offers',
            method: 'GET',
            headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              },
            params: {
                "pageNum": arr
            }
        }).then((res) => {
            callback(res.data);
            console.log(res.data)
        })
    }

    // 주문취소
    const cancelOffer = (id) => {

        const paramData = {
            offerId: id
        }

        const param = JSON.stringify(paramData);

        axios({
            url: '/offer/'+id+"/cancel",
            method: 'POST',
            headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              },
            data: param
        }).then((res) => {
            callback(res.data);
            if(res.data.problem != false) {
                alert("주문을 취소하던 중 문제가 발생하였습니다.")
            }
            window.location.href='/offer/OfferHist';
        })
    }

    return(
        <>
            {testStr.offerItem && testStr.offerItem.map((offerItem) => {
                <li key={offerItem.id} />
                return(
                    <div className="offerHistListForm">
                    <div class="d-flex mb-3 align-self-center">
                        <h4 >{offerItem.date}  </h4>
                        <div class="ml-3">

                            {
                                offerItem.status == "Offer"
                                ? <button type="button" class="btn btn-outline-secondary"  
                                onClick={()=>cancelOffer(offerItem.id)}> 주문취소</button>
                                : <h4>(취소 완료)</h4>
                            }

                        </div>
                    </div>

                    <div class="cardOfferHist">
                    <div class="d-flex mb-3">

                        <div class="repImgDiv">
                            <img src={offerItem.itemDtoList[0].imgUrl} class = "imgOfferHist" />
                        </div>

                        <div class="contentsOfferHist">
                            <span  class="fs24 font-weight-bold">{offerItem.itemDtoList[0].itemNm}</span>
                            <div class="fs18 font-weight-light">
                                <span>{offerItem.itemDtoList[0].count}</span>
                            </div>
                            <div class="fs18 font-weight-light">
                                <span>구매자: </span>
                                <span >{offerItem.itemDtoList[0].itemBuyer}</span>
                            </div>
                            {/* <div class="fs18 font-weight-light">
                                <span>배송번호: </span>
                                <span >{offerItem.logistic}</span>
                            </div> */}
                        </div>
                        
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
    )
}

export default OfferHistList;