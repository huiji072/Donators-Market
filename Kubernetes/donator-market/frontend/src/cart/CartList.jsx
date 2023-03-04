import React, { useState, useEffect } from 'react';
import axios from 'axios'
import './Cart.css';

function CartList() {

    const [ testStr, setTestStr ] = useState('');
    // 변수 초기화
    function callback(str) {
      setTestStr(str);
    }

    useEffect(
        () => {
          axios({
              url: '/carts',
              method: 'GET',
              headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              }
          }).then((res) => {
              callback(res.data);
              if(res.data.problem != false) {
                alert("상품을 장바구니에 담던 중 문제가 발생하였습니다.");
              }
          })
        }, []
    );

    //장바구니 취소
    const deletedCart = id => {
        axios({
            url: '/cartItem/'+id,
            method: 'DELETE',
            headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              }
        }).then((res) => {
            callback(res.data);
            if(res.data.problem != false) {
                alert("상품을 삭제하던 중 문제가 발생하였습니다.");
            } else {
            alert("해당 상품이 삭제되었습니다.");
            window.location.href="/cart/Cart"
            }
        }).catch((error) => {
            if(error.response.status === 403 || error.response.status === 400) {
                alert("주문 권한이 없습니다.")
                window.location.href = "/members/login"
            }
        })
    }
 
    // 구매수량 가져오기
    const [orderStockNumber, setOrderStockNumber] = useState("1");

    const handleChangeOrderStockNumber = (count, event) => {
        setOrderStockNumber(event.target.value)
    }    

    // 체크박스 핸들러
    const [checkItems, setCheckItems] = useState([]);

    // 체크박스 하나씩 선택
    const handleCheckbox = (checked, id) => {

        if(checked) {
            setCheckItems(prev => [...prev, id]);
        } else {
            setCheckItems(checkItems.filter((el) => el != id));
        }
        
    }

    //체크박스 전부 선택
    const checkAll = (checked) => {
        const {cartItem} = checkItems;

        if(checked) {
            const idArray = [];
            testStr.cartItem.forEach((el) => idArray.push(el.itemId));
            setCheckItems(idArray);
        } else {
            setCheckItems([]);
        }
    }


    // 주문하기

    const dataList = new Array();
    const dataList2 = new Array();
    const orderParamData = new Object();
    const offerParamData = new Object();
    const arr = new Array();

    const orders = () => {

        arr.push(checkItems);

        for(let i=0; i<checkItems.length; i++){
            const data = new Object();
            data["cartItemId"] = checkItems[i];
            dataList.push(data);
            dataList2.push(data);
        }

        orderParamData['cartOrderDtoList'] = dataList;
        offerParamData['cartOfferDtoList'] = dataList2;
        const orderParam = JSON.stringify(orderParamData);
        const offerParam = JSON.stringify(offerParamData);

        let formData = new FormData();
        formData.append("data", new Blob([orderParam], {type: "application/json"}));
        formData.append("data2", new Blob([offerParam], {type: "application/json"}));



        axios({
            url: '/cart/orders', 
            method: 'POST',
            headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              },
            data: formData
        }).then((res) => {
            callback(res.data);
            if(res.data.problem != false) {
                alert("주문 중 문제가 발생하였습니다.");
            } else {
                alert("주문이 완료되었습니다.");
            }
        })

    }



    return(
        <>
        <div class="cartContainer">
                <div>
                    <h2 class="mb-4">장바구니 목록</h2>
                <div>

                <table class="table">
                    <thead>
                    <tr class="cartListTr1">
                        <td>
                            <input type="checkbox" id="checkall" onChange={(e)=>checkAll(e.target.checked)}/> 
                        </td>
                        <td>상품정보</td>
                    </tr>
                    </thead>
                    <tbody>
                    {testStr.cartItem && testStr.cartItem.map((cartItem, id) => {
            <li key={cartItem.itemId}/>
                return(
                    <>
                    <tr>
                    <td class="text-center align-middle">
                        <input type="checkbox" name="cartItem" value={cartItem.itemId}
                        onChange={(e)=>handleCheckbox(e.target.checked, cartItem.itemId)}
                        checked={checkItems.includes(cartItem.itemId) ? true : false}/>
                    </td>
                    <td class="d-flex">
                        <div >
                        <img src={"https://shopitemimage.s3.ap-northeast-2.amazonaws.com/itemImg/"+cartItem.oriImgName} class = "cartImg"/>
                        </div>
                        <div class="align-self-center">
                            <span class="fs24 font-weight-bold">{cartItem.itemNm}</span>
                            <div class="fs18 font-weight-light">
                                <span class="input-group mt-2">

                                    <input type="text" name="count" 
                                         min="1" value={cartItem.count}  class="form-control mr-2"
                                         onChange={(e) => handleChangeOrderStockNumber(cartItem.count, e)}/>
                                    <button type="button" class="close" aria-label="Close" onClick={()=>deletedCart(cartItem.itemId)}>
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </span>
                            </div>
                        </div>
                    </td>
                </tr>
                </>
                
                )
                
            })}
                    </tbody>
                </table>

                <div class="text-center mt-3">
                    <button type="button" class="btn btn-primary btn-lg" onClick={()=>orders()}>
                        주문하기
                    </button>
                </div>
                
                </div>
            </div>

            </div>
       </>
    )
}

export default CartList;