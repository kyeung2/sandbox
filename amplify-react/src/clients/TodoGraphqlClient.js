import {API, graphqlOperation} from "aws-amplify";
import * as queries from "../graphql/queries";
import * as mutations from "../graphql/mutations";

const listTodos = async () => {
    const response = await API.graphql(graphqlOperation(queries.listTodos));
    return response.data.listTodos.items;
};

const addTodo = async (name, description) => {
    const todoDetails = {name, description};
    const response = await API.graphql(graphqlOperation(mutations.createTodo, {input: todoDetails}));
    return response.data.createTodo;
};

const updateTodo = async (id, name, description) => {
    const todoDetails = {id, name, description};
    const response = await API.graphql(graphqlOperation(mutations.updateTodo, {input: todoDetails}));
    return response.data.updateTodo
};

const deleteTodo = async (id) => {
    const todoDetails = {id};
    const response = await API.graphql(graphqlOperation(mutations.deleteTodo, {input: todoDetails}));
    console.log(response)
};

const TodoGraphqlClient = {
    listTodos,
    addTodo,
    updateTodo,
    deleteTodo
};


export default TodoGraphqlClient