import { useEffect, useState } from "react";
import { Button, Container, Row, Table } from "react-bootstrap";
import { deleteById, getAllByUserId } from "../../../service/serviceProject";
import { Project } from "../../../types/project";
import { ModalDetails } from "../ModalDetails";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/ReactToastify.css";
import { ModalEdit } from "../ModalEdit";
import { User } from "@supabase/supabase-js";
import { getUser } from "../../../service/auth";

export function List() {
  const [projects, setProjects] = useState<Project[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [user, setUser] = useState<User>();

  useEffect(() => {
    checkUser();
    handleListProjects();
  }, [loading]);

  function checkUser() {
    getUser().then((user) => {
      if (user !== null) {
        setUser(user);
      }
    });
  }

  function handleListProjects() {
    setLoading(false);
    getAllByUserId(user?.id)
      .then((projects) => {
        setProjects(projects);
      })
      .finally(() => {
        setLoading(true);
      })
  }

  function handleRemove(id: string) {
    deleteById(id)
      .then(() => {
        toast.success("project removed");
      })
      .catch((e) => {
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
