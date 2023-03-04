import React from 'react';
import axios from 'axios';

const Form = () => {
  // a local state to store the currently selected file.
  const [selectedFile, setSelectedFile] = React.useState(null);

  const handleSubmit = (event) => {
    event.preventDefault()
    const formData = new FormData();
    formData.append("selectedFile", selectedFile);
    try {
      axios({
        method: "post",
        url: "/item/new/test",
        headers: { "Content-Type": "multipart/form-data" },
        data: {"imgFile": selectedFile}
      });
    } catch(error) {
      console.log(error)
    }
    
    console.log(formData)
  }

  const handleFileSelect = (event) => {
    setSelectedFile(event.target.files[0])
  }

  return (
    <form onSubmit={handleSubmit}>
      <input type="file" onChange={handleFileSelect}/>
      <input type="submit" value="Upload File" />
    </form>
  )
};

export default Form;