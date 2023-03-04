import React, { useState, useEffect } from 'react';
import axios from 'axios'
import './MemberManagement.css';

function MemberManagement() {

    const [ memberMng, setMemberMng ] = useState('');
    const[pageNum, setPageNum] = useState(0);
    const maxPage = 10;

    // 변수 초기화
    function callback(str) {
      setMemberMng(str);
    }

    useEffect(
        () => {
          axios({
              url: '/members/managements',
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
              if(res.data.problem != false) {
                alert("회원 정보를 불러오는 중 문제가 발생하였습니다.")
              }
          }).catch((error) => {
            if(error.response.status === 403 || error.response.status === 400) {
                alert("조회 권한이 없습니다.")
                window.location.href = "/members/login"
            }
        })
        }, []
    );

    const prevPage = (e) => {
        e.preventDefault();
        setPageNum(pageNum - 1);
        axios({
            url: '/members/managements',
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

    const nextPage = (pageNum, e) => {
        e.preventDefault();
        setPageNum(pageNum + 1);
        axios({
            url: '/members/managements',
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

    const clickPage = (arr, e) => {
        e.preventDefault();
        setPageNum(arr)
        axios({
            url: '/members/managements',
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
        })
    }

    const arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


    return(
        <>
        <div className='memberMngContainer'>
            <div>
            <h2 class="mb-4">회원 목록</h2>
            </div>


            <div>
                <table className="table">
                    <thead>
                    <tr>
                        <td>아이디</td>
                        <td>이름</td>
                        <td>이메일</td>
                        <td>주소</td>
                        <td>권한</td>
                    </tr>
                    </thead>

                    <tbody> 
                    {memberMng.memberManagement && memberMng.memberManagement.map((member) => {
                    <li key={member.id}/>
                    return(
                        <tr>
                            <td>{member.id}</td>
                            <td>{member.name}</td>
                            <td>{member.email}</td>
                            <td>{member.address}</td>
                            <td>{member.role}</td>
                        </tr>
                    )})}
                    </tbody>

                </table>
            </div>


        </div>

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
    );
}

export default MemberManagement;