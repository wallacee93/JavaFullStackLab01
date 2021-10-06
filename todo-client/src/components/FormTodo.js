import React, {useState} from "react";
import { Button, Form } from 'react-bootstrap';

export default function FormTodo({ addTodo }) {
    const [value, setValue] = useState("");
  
    const handleSubmit = e => {
      e.preventDefault();
      if (!value) return;
      addTodo({text:value});
      setValue("");
    };
  
    return (
        <Form onSubmit={handleSubmit}> 
        <div className="row">
          <div className="col-10">
            <Form.Group>
              <Form.Control type="text" className="input" value={value} onChange={e => setValue(e.target.value)} placeholder="Add new todo" />
            </Form.Group>
            </div>
            <div className="col-2">
            <Button variant="primary mb-3" type="submit">
              Submit
            </Button>
          </div>
        </div>
      </Form>
    );
  }