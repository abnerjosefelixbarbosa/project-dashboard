import { useEffect, useState } from "react";
import { Button, Container, Row, Table } from "react-bootstrap";
import { deleteProjectById, listProjectsAllByAccountId } from "../../../service/serviceProject";
import { ModalDetails } from "../ModalDetails";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/ReactToastify.css";
import { ModalEdit } from "../ModalEdit";
import { useSearchParams } from "react-router-dom";
import { Project } from "../../../entities/project";

export function List() {
  const [projects, setProjects] = useState<Project[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [params] = useSearchParams();
  const token = params.get('token')!;
  const accountId = params.get('accountId')!;

  useEffect(() => {
    handleListProject();
  }, [loading]);

  async function handleListProject() {
    try {
      const res = await listProjectsAllByAccountId(accountId, token);
      setProjects(res);
    } catch (e: any) {
      const message = `${e.message}`;
      toast.error(message);
    }
  }

  function handleRemove(id: string) {
    setLoading(false);
    deleteProjectById(id, token).then(() => {
      setLoading(true);
    }).catch((e) => {
      toast.error(e.message);
    });
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
                      <span>{project.dateStart.toString()}</span>
                    </td>
                    <td align="center">
                      <span>{project.dateEnd.toString()}</span>
                    </td>
                    <td align="center">
                      <span>{project.budget.toFixed(2)}</span>
                    </td>
                    <td align="center">
                      <ModalDetails description={project.description} />
                    </td>
                    <td align="center">
                      <ModalEdit
                        id={project.id}
                        name={project.name}
                        description={project.description}
                        dateStart={project.dateStart}
                        dateEnd={project.dateEnd}
                        budget={project.budget}
                        accountId={project.account.id}
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
