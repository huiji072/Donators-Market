import React, { useState, useEffect } from 'react';
import axios from 'axios'
import './ItemForm.css';
import { useParams } from 'react-router-dom';

function ItemUpdate(props) {

    let formData = new FormData();

    const [ testStr, setTestStr ] = useState('');
    // 변수 초기화
    function callback(str) {
      setTestStr(str);
    }

    function itemNew(e){
        e.preventDefault();
    }

    const[itemSellStatus, setItemSellStatus] = useState("SELL");
    const[itemNm, setItemNm] = useState("");
    const[stockNumber, setStockNumber] = useState("");
    const[itemDtl, setItemDtl] = useState("");
    const[itemImgIds, setItemImgIds] = useState("");
    const[itemId, setItemId] = useState("");

    const handleChangeItemSellStatue = (event) => {
        setItemSellStatus(event.target.value);
    }

    const handleChangeItemNm = (event) => {
        setItemNm(event.target.value);
    }

    const handleChangeStockNumber = (event) => {
        setStockNumber(event.target.value);
    }

    const handleChangeItemDtl = (event) => {
        setItemDtl(event.target.value);
    }

    const handleChangeItemImgIds = (itemImgIds) => {
        setItemImgIds(itemImgIds);
    }

    const handleChangeItemId = () => {
        setItemId(testStr.item[0].id)
    }

    const onFileChange = (e) => {
        formData.append("imgFile", e.target.files[0]);
    }

    const params = useParams();

    useEffect(
        () => {
            const itemId = params.itemId;
          axios({
              url: '/admin/item/'+itemId,
              method: 'GET',
              headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              }
          }).then((res) => {
              callback(res.data);

              if(res.data.problem != false) {
                alert("상품 수정 중 문제가 발생하였습니다.");
              }

              setItemImgIds(res.data.item[0].itemDtoList[0].id);
              setItemId(res.data.item[0].id)
          }).catch((error) => {
            if(error.response.status === 403 || error.response.status === 400) {
                alert("권한이 없습니다.")
                window.location.href = "/members/login"
            }
        })
        }, []
    );

    
    const data = {
        itemSellStatus: itemSellStatus,
        itemNm: itemNm,
        stockNumber: stockNumber,
        itemDetail: itemDtl,
        id: itemId,
        itemImgIds: [itemImgIds]
    }
    
    const jsonData = JSON.stringify(data);

    formData.append("data", new Blob([jsonData], {type: "application/json"}));

    // 상품 수정
    const updateItem = () => {
        const itemId = params.itemId
        axios({
            url: '/admin/item/'+itemId,
            method: 'POST',
            headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'multipart/form-data' 
            },
            data : formData
        }).then((res) => {
            callback(res.data);
            if(res.data.problem != false) {
                alert("상품 수정 중 문제가 발생하였습니다.");
            }
        })
        window.location.href = "/";
    }

        return(
            <div className="itemFormContainer">
            {testStr.item && testStr.item.map ((item) => {
                <li key={item.id}/>
                return(
                    <>
                     <div>
                    <h2>상품 수정</h2>
                    
                </div>
            <form onSubmit={itemNew}>

                <div>
                    <input type='hidden' onChange={handleChangeItemId} />
                </div>

                <div class="form-group">
                    <select class="form-select" value={itemSellStatus} onChange={handleChangeItemSellStatue}>
                        <option value="SELL">판매중</option>
                        <option value="SOLD_OUT">품절</option>
                    </select>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">상품명</span>
                        
                    </div>
                    <input value={itemNm} onChange={handleChangeItemNm} 
                    class="form-control" type="text" placeholder="상품명을 입력해주세요"></input>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">재고</span>
                    </div>
                    <input value={stockNumber} onChange={handleChangeStockNumber}
                    class="form-control" type="number" placeholder="상품의 재고를 입력해주세요"></input>
                </div>

                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">상품 상세 내용</span>
                    </div>
                    <textarea value={itemDtl} onChange={handleChangeItemDtl}
                    class="form-control"></textarea>   

                </div>

                <div>
                    <input type="file" name="imgFile" onChange={onFileChange}/>
                    <input type="hidden" name="itemImgIds"
                    onChange={()=>handleChangeItemImgIds(item.itemDtoList[0].id)}/>
                </div>
        

                {/* 수정 버튼 */}
                <div className="d-grid">
                    <button type="submit" className="btn btn-primary"
                    onClick={()=>updateItem()}
                    formAction={'/admin/item/'+item.id}
                    >
                        Update
                    </button>
                </div>

            </form>
                    </>
                );
               
            })}
            </div>
        );
}

export default ItemUpdate;