import React, { useState, useEffect } from 'react';
import axios from 'axios'
import './ItemForm.css';
import AWS from "aws-sdk"
import { set } from 'react-hook-form';

function ItemForm(props) {

    // s3 설정
    const ACCESS_KEY = 'AKIAVI3DPJDJLXGB6QBM';
    const SECRET_ACCESS_KEY = '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';
    const REGION = "ap-northeast-2";
    const S3_BUCKET = 'shopitemimage';

    AWS.config.update({
        accessKeyId: ACCESS_KEY,
        secretAccessKey: SECRET_ACCESS_KEY
      });
      
      const myBucket = new AWS.S3({
        params: { Bucket: S3_BUCKET},
        region: REGION,
      });




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

    // const handleChangeItemImgDto = (e) => {
    //     formData.append("imgFile", e.target.files[0]);
    // }

    const [img, setImg] = useState('')

    const onFileChange = (e) => {
        setImg(e.target.files[0])
    }
    const file = img;
    formData.append("imgFile", img);
   

    const data = {
        itemSellStatus: itemSellStatus,
        itemNm: itemNm,
        stockNumber: stockNumber,
        itemDetail: itemDtl
    }

    const jsonData = JSON.stringify(data)

    const uploadImageFile = () => {
        const params = {
            ACL: 'public-read',
            Body: file,
            Bucket: S3_BUCKET,
            Key: "itemImg/" + file.name
          };
    
          myBucket.putObject(params)
          .on('httpUploadProgress', (evt) => {
            setTimeout(() => {
              setImg(null);
            }, 3000)
          })
          .send((err) => {
            if (err) console.log(err)
          })
    } 


    
    formData.append("data", new Blob([jsonData], {type: "application/json"}));

    // 상품 등록
    const sendItemRegisterRequest = (event) => {

        // s3에 이미지 등록
        uploadImageFile();
        
        axios({
            url: '/admin/item/new',
            method: 'POST',
            headers: { 
                'Authorization' : 'Bearer ' + localStorage.getItem("ACCESS_TOKEN"),
                'Content-Type': 'application/json' 
              },
            data : formData
        }).then((res) => {
            callback(res.data);
            if(res.data.problem != false) {
                alert("상품 등록 중 에러가 발생하였습니다.");
            }
            window.location.href = "/";
        }).catch((error) => {
            if(error.response.status === 403 || error.response.status === 400) {
                alert("상품 등록 권한이 없습니다.")
                window.location.href = "/members/login"
            }
        })



    }

        return(
            <div className="itemFormContainer">

                <div>
                    <h2>상품 등록</h2>
                </div>

            <form onSubmit={itemNew}>
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

                {/* 이미지 */}

                {/* <div >
                    <div class="form-group" each="itemImgDto, status: ${itemFormDto.itemImgDtoList}">
                        <div class="custom-file img-div">
                            <input onChange={handleChangeItemImgDto}
                            type="file" class="custom-file-input" name="imgFile"/>
                            <input type="hidden" name="itemImgIds" value="${itemImgDto.id}"></input>
                            <label class="custom-file-label" ></label>
                        </div>
                    </div>
                </div> */}

                <div>
                    <input type="file" name="imgFile" onChange={onFileChange}/>
                </div>
            

                {/* 저장 버튼 */}
                <div className="d-grid">
                    <button className="btn btn-primary"
                    onClick={() => sendItemRegisterRequest()}
                    formAction='/admin/item/new'
                    >
                        Submit
                    </button>
                </div>

                {/* 수정 버튼 */}
                {/* <div className="d-grid">
                    <button type="submit" className="btn btn-primary"
                    onClick={()=>updateItem()}
                    formAction='/admin/item/334'
                    >
                        Update
                    </button>
                </div> */}

            </form>
            </div>
        );
}

export default ItemForm;