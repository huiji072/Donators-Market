import React from "react";
import './Comment.css';

function Comment() {



    return (
        <>
<div className="commentWrapper2">
                <div>
                    <img className="commentImage"
                    src="/img/comment.png" />
                </div>

                <div className="commentCotentContainer">
                    <span className="commentNameText"></span>
                    <span className="commentRegTime"></span>
                    <span className="commentCommentText"></span>                    
                </div>
            </div>
        
        </>

    );
}

export default Comment;