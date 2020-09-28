import React, {useEffect, useState} from 'react';
import TodoItem from "./TodoItem";
import AddTodo from "./AddTodo";
import TodoGraphqlClient from "../clients/TodoGraphqlClient";

// TODO following these tutorials
// https://medium.com/better-programming/how-to-fetch-data-from-an-api-with-react-hooks-9e7202b8afcd
// https://youtu.be/S66rHpyU-Eg?t=2238

function TodoList() {

    const [todoList, setTodoList] = useState([]);

    const refreshTodoList = async () => {
        const todoList = await TodoGraphqlClient.listTodos();
        setTodoList(todoList)
    };

    useEffect(() => {
        (async () => {
            await refreshTodoList();
        })();
    }, []);

    const onAdd = async (name, description) => {
        await TodoGraphqlClient.addTodo(name, description);
        await refreshTodoList();//TODO expensive look into subscriptions
    };

    const onEditSubmit = async (id, name, description) => {
        await TodoGraphqlClient.updateTodo(id, name, description);
        await refreshTodoList();//TODO expensive look into subscriptions
    };

    const onDelete = async id => {
        await TodoGraphqlClient.deleteTodo(id);
        await refreshTodoList();//TODO expensive look into subscriptions
    };

    return (
        <div className="todo-list">
            <h4>TodoList with CRUD operations from GraphQL API :)</h4>
            <AddTodo onAdd={onAdd}/>
            <hr/>
            {
                todoList.map(todo => {
                    return (
                        <TodoItem key={todo.id} {...todo} onDelete={onDelete} onEditSubmit={onEditSubmit}/>
                    )
                })
            }
        </div>
    )
}

export default TodoList