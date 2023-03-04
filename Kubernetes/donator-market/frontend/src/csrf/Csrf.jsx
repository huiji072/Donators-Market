import { useState,useEffect } from "react";

const csrfProtection = csrf({
    cookie: true
  });
  app.use(csrfProtection);
  app.get('/getCSRFToken', (req, res) => {
    res.json({ CSRFToken: req.CSRFToken() });
  });

  const getCSRFToken = async () => {
    const response = await axios.get('/getCSRFToken');
    axios.defaults.headers.post['X-CSRF-Token'] = response.data.CSRFToken;
 };

export default function Profile(){
    const [user,setUser]=useState();
    const getUsers=async()=>{
        const response=await fetch('https://randomuser.me/api/');
        const data=await response.json();
        setUser(data.results[0])
    }
    useEffect(()=>{
        getUsers();
        getCSRFToken();
    },[])
    const handleDelete=()=>{}
    return(
        <div className="users">
        <div className="user">
            <div className="user__img">
                <img src={user.picture.thumbnail}/>
            </div>
            <div className="user__name">
                {user.name.first +" "+ user.name.last}
            </div>
            <div className="delete" onClick={handleDelete}>
                DELETE
            </div>
        </div>
        </div>
    
    )

}
