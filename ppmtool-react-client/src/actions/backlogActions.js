import axios from "axios";

export const addProjetTask = (
    backlogId,
    projectTask,
    history
) => async dispatch => {
    await axios.post(`/api/backlog/${backlogId}`, projectTask);

    history.push(`/projectBoard/${backlogId}`);
};
