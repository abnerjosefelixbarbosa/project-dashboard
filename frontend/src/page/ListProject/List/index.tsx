import { useEffect, useState } from "react";
import { Container, Row, Table } from "react-bootstrap";
import { getAllByUserId as serviceGetAllByUserId } from "../../../service/serviceProject";
import { Project } from "../../../types/project";

interface UserData {
  userId: string;
}

export function List({ userId }: UserData) {
  const [projects, setProjects] = useState<Project[]>([]);

  useEffect(() => {
    getAllByUserId();
  }, [setProjects]);

  function getAllByUserId() {
    //console.log(userId);
    serviceGetAllByUserId(userId).then((value) => {
      setProjects(value!);
    });
  }

  return (
    <>
      <Container className="container_project_list">
        <Row className="table_project_list">
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>name</th>
                <th>description</th>
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
                      <span>{project.description}</span>
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
