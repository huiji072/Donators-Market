import React, { useState, useEffect } from 'react';
import axios from 'axios'

function Logout() {

  localStorage.setItem("ACCESS_TOKEN", null);
  window.location.href = "/";

    // axios({
    //     url: '/members/logout',
    //     method: 'get'
    //   }).then((res) => {
    //     callback(res.data);
    //     alert("aaa")
    //     localStorage.setItem("ACCESS_TOKEN", null);
    //     window.location.href = "/";
    //   }).catch((error) => {
    //     console.log(error);
    //     alert(error)
    //   })


}

export default Logout;