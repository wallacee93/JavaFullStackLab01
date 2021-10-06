import React, {useState, useEffect} from "react";
import "./App.css";
import { Card } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import Todo from "./components/Todo";
import FormTodo from "./components/FormTodo";
import { Snackbar } from '@mui/material';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import MuiAlert from '@mui/material/Alert';
import axios, {get, post} from 'axios';

const baseUrl = "http://localhost:8082/api/v1";
const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

function App() {
  const [update, setUpdate] = useState(true);
  const [todos, setTodos] = useState([

  ]);

  const [msg, setMsg] = useState("");
  const [open, setOpen] = useState(false);
  const vertical = 'top';
  const horizontal = 'center';

  const config = {
    headers: {
      'Content-Type': 'application/json',
    }
  }

  useEffect(()=>{
    if(update){
      get(`${baseUrl}/todos`)
      .then(response=> {
        setTodos(response.data);
      })
      .catch(error => {
        displayError("Failed to connect");
      });
      setUpdate(false);
    }

  },[update]);

  const displayError = (msg) => {
    setMsg(msg);
    setOpen(true);
  };

  const handleClose = (event, reason) => {
    setOpen(false);
  };

  const addTodo = text => {
    post(`${baseUrl}/todos`, JSON.stringify(text),config)
    .then(response => {
      setUpdate(true);
    });
    
  };

  const markTodo = index => {
    axios.put(`${baseUrl}/todos/${index}`)
    .then(response => {
      setUpdate(true);
    });
  };

  const removeTodo = index => {
    axios.delete(`${baseUrl}/todos/${index}`).then(response => {
      setUpdate(true);
    })

  };

  const action = (
    <>
      <IconButton
        size="small"
        aria-label="close"
        color="inherit"
        onClick={handleClose}
      >
        <CloseIcon fontSize="small" />
      </IconButton>

      
    </>
  );

  const renderEmpty = () => {
    if(todos.length === 0)
      return (
      <>
        <div className="row">
          <div className="col-12">
            <h3 className="text-center">No Todos Listed</h3>
          </div>
        </div>
      </>);
  }
  return (
    <div className="app">
      <div className="container">
        <h1 className="text-center mb-4">To Do List</h1>
        <FormTodo 
          addTodo={addTodo} 
        />
        
        <div className="row">
          {renderEmpty()}
          {todos.map((todo, index) => (
            <Card key={index}>
              <Card.Body>
                <Todo
                key={index}
                index={index}
                todo={todo}
                markTodo={markTodo}
                removeTodo={removeTodo}
                />
              </Card.Body>
            </Card>
          ))}
        </div>
      </div>
      <Snackbar
          open={open}
          anchorOrigin={{ vertical, horizontal }}
          autoHideDuration={6000}
          onClose={handleClose}
          action={action}
        >
          <Alert onClose={handleClose} severity="error" sx = {{width: '100%'}}>
            {msg}
          </Alert>
        </Snackbar>
    </div>
  );
}

export default App;