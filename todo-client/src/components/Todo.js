import React from "react";
import { Button } from 'react-bootstrap';

export default function Todo({ todo, index, markTodo, removeTodo }) {  
    return (
      <div className="todo">
        <span style={{ textDecoration: todo.isDone ? "line-through" : "" }}>{todo.text}</span>
        <div>
          <Button variant="outline-success" onClick={() => markTodo(todo.id)}>✓</Button>{' '}
          <Button variant="outline-danger" onClick={() => removeTodo(todo.id)}>✕</Button>
        </div>

      </div>
    );
  }