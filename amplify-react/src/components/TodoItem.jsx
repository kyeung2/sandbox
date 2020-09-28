import React, {useState} from 'react';

import EditProduct from "./EditTodo";

function TodoItem({id, name, description, ...props}) {

    const [isEdit, setEdit] = useState(false);

    const onEdit = () => {
        setEdit(true)
    };

    const onDelete = () => {
        props.onDelete(id)
    };

    const onEditSubmit = (id, name, description) => {
        props.onEditSubmit(id, name, description);
        setEdit(false)
    };

    return (
        <div>
            {
                isEdit ? (
                    <EditProduct id={id} originalName={name} originalDescription={description} onEditSubmit={onEditSubmit}/>
                ) : (
                    <div>
                        <span>{name}</span>
                        {`: `}
                        <span>{description}</span>
                        {` ( `}
                        <button onClick={onEdit}>Edit</button>
                        {` , `}
                        <button onClick={onDelete}>Delete</button>
                        {` )`}
                    </div>
                )
            }
        </div>
    )
}

export default TodoItem