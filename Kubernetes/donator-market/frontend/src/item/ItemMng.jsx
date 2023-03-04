import React, { useState, useEffect } from 'react';
import axios from 'axios'
import './ItemMng.css';

function ItemMng(props) {

    const [ testStr, setTestStr ] = useState('');
    const[pageNum, setPageNum] = useState(0);
    const[itemMngCount, setItemMngCount] = useState(0)

    // 변수 초기화
    function callback(str) {
    setTestStr(str);
    }

    
    useEffect(
        () => {
         axios({
            url: '/item/itemMng',
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
            setItemMngCount(res.data.itemMngCount);
            if(res.data.problem != false) {
                alert("상품 목록을 불러오던 중 문제가 발생하였습니다.");
            }
        }).catch((error) => {
            if(error.response.status === 403 || error.response.status === 400) {
                alert("주문 권한이 없습니다.")
                window.location.href = "/members/login"
            }
        })
        }, []
); 

let arr = []
for(let i = 0 ; i < Math.ceil(itemMngCount/10) ; i++) {
    arr.push(i+1)
}

    const prevPage = (e) => {
        e.preventDefault();
        if (pageNum === 0) {
            alert("첫번째 페이지 입니다.")
        } else {
            setPageNum(pageNum - 1);
            axios({
                url: '/item/itemMng',
                method: 'GET',
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
        if(pageNum >= Math.ceil(itemMngCount/10) - 1) {
            alert("마지막 페이지입니다.")
        } else {
            setPageNum(pageNum + 1);
            axios({
                url: '/item/itemMng',
                method: 'GET',
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
        setPageNum(arr)
        axios({
            url: '/item/itemMng',
            method: 'GET',
            params: {
                "pageNum": arr
            }
        }).then((res) => {
            callback(res.data);
        })
    }


// 상품명 검색
    const[search, setSearch] = useState('')
    const handleSearchChange = (event) => {
        setSearch(event.target.value);
    }

    // 판매상태 검색
    const [searchSellStatus, setSearchSellStatus] = useState('');
    const HandleOptionOrderState = (e) => {
        setSearchSellStatus(e.target.value);
    }


    const handleSearchClick = (e) => {
        e.preventDefault();
        axios({
            url: '/item/search',
            method: 'GET',
            params: {
                "searchName": search,
                "searchStatus": searchSellStatus
            }
        }).then((res) => {
            callback(res.data);
            if(res.data.problem != false) {
                alert("상품 검색 중 문제가 발생하였습니다.")
            }
        })
    }



        return(
            <div className="containerItemMng">


            <form role="form" method="get" className='itemMngForm'>
                <table className="table">
                    <thead>
                    <tr>
                        <td>상품아이디</td>
                        <td>상품명</td>
                        <td>상품개수</td>
                        <td>상태</td>
                        <td>등록자</td>
                        <td>등록일</td>
                    </tr>
                    </thead>

                    <tbody> 
                    {testStr.item && testStr.item.map((item) => {
                <li key={item.id}/>
                return(
                        <tr>
                            <td >{item.id}</td>
                            <td>
                                <a href={'/item/update/'+item.id}>{item.name}</a>
                            </td>
                            <td>{item.stockNumber}</td>
                            <td>{item.sellStatus}</td>
                            <td>{item.createdBy}</td>
                            <td>{item.regTime}</td>
                        </tr>
                )
            })}
                    </tbody>

                </table>


                <div class="form-control-main-itemMng">
                    {/* <select  >
                        <option value="all">전체기간</option>
                        <option value="1d">1일</option>
                        <option value="1w">1주</option>
                        <option value="1m">1개월</option>
                        <option value="6m">6개월</option>
                    </select> */}
                    <select  onChange={HandleOptionOrderState}>
                        <option value="ALL">판매상태(전체)</option>
                        <option value="SELL">판매</option>
                        <option value="SOLD_OUT">품절</option>
                    </select>
                    <select >
                        <option value="itemNm">상품명</option>
                    </select>

                    <input type="text" placeholder="검색어를 입력해주세요"
                    value={search} onChange={handleSearchChange}/>
                    <button id="searchBtn" type="submit" className="btn btn-primary"
                     onClick={handleSearchClick}
                    >검색</button>
                </div>
            </form>

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

            </div>
        );
}

export default ItemMng;