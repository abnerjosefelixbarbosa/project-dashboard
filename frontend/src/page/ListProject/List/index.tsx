import { useEffect, useState } from "react";
import { Button, Container, Row, Table } from "react-bootstrap";
import { listProjectsAllByAccountId } from "../../../service/serviceProject";
import { Project } from "../../../types/project";
import { ModalDetails } from "../ModalDetails";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/ReactToastify.css";
import { ModalEdit } from "../ModalEdit";
import { useLocation } from "react-router-dom";

export function List() {
  const [projects, setProjects] = useState<Project[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const location = useLocation();

  useEffect(() => {
    handleListProject();
  }, [loading]);

  async function handleListProject() {
    const token = location.state.token;
    const accountId = location.state.accountId;
    const res = await listProjectsAllByAccountId(accountId, token);
    setProjects(res);
  }

  function handleRemove(id: string) {
    /*
    setLoading(false)
    deleteById(id)
      .then(() => {
        toast.success("project removed", {
          autoClose: 2000,
          position: "top-center",
        });
      })
      .catch((e) => {
        toast.error(e.message);
      })
      .finally(() => {
        setLoading(true)
      });
      */
  }

  return (
    <>
      <ToastContainer />
      <Container className="container_project_list">
        <Row className="table_project_list">
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>name</th>
                <th>start</th>
                <th>end</th>
                <th>budget</th>
              </tr>
            </thead>
            <tbody>
              {projects?.map((project) => {
                return (
                  <tr key={project.id}>
                    <td align="center">
                      <span>{project.name}</span>
                    </td>
                    <td align="center">
                      <span>{project.start.toString()}</span>
                    </td>
                    <td align="center">
                      <span>{project.end.toString()}</span>
                    </td>
                    <td align="center">
                      <span>{project.budget}</span>
                    </td>
                    <td align="center">
                      <ModalDetails description={project.description} />
                    </td>
                    <td align="center">
                      <ModalEdit
                        id={project.id}
                        name={project.name}
                        description={project.description}
                        start={project.start}
                        end={project.end}
                        budget={project.budget}
                        userId={project.user_id}
                      />
                    </td>
                    <td align="center">
                      <Button
                        variant="danger"
                        onClick={() => handleRemove(project.id)}
                      >
                        Remove
                      </Button>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </Table>
        </Row>
      </Container>
    </>
  );
}
