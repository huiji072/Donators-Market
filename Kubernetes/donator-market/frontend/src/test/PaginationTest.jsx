// import React, {useState, useEffect, useParams} from "react";
// import axios from "axios";
// import Posts from "./Posts";
// import Pagination from "./Pagination";

// function PaginationTest () {

//     const[posts, setPosts] = useState([]);
//     const [loading, setLoading] = useState(false);
//     const [currentPage, setCurrentPage] = useState(1);
//     const [postPerPage, setPostPerPage] = useState(3);

//     const paginate = (pageNumber) => setCurrentPage(pageNumber);

//     // const { itemId } = useParams();
//     // const url = '/item/'+itemId
    
//     const [ testStr, setTestStr ] = useState('');
//     // 변수 초기화
//     function callback(str) {
//       setTestStr(str);
//     }

//     useEffect(
//         () => {
//           axios({
//               url: '/item/334',
//               method: 'GET'
//           }).then((res) => {
//               callback(res.data);
//               console.log(res.data.comment);
//               setPosts(res.data.comment)
//           })
//         }, []
//     );

//     const indexOfLastPost = currentPage * postPerPage;
//     const indexOfFirstPost = indexOfLastPost - postPerPage;
//     const currentPosts = posts.slice(indexOfFirstPost, indexOfLastPost);

//     console.log(posts)

//     return(
//         <>
//         <Posts posts={currentPosts} loading={loading}/>
//         <Pagination postsPerPage={postPerPage} totalPosts={posts.length} 
//         paginate={paginate}/>
//         </>
//     );
// }

// export default PaginationTest;