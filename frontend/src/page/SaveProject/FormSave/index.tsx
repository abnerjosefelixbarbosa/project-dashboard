import { Button, Container, Form, Row } from "react-bootstrap";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { save as serviceSave } from "../../../service/serviceProject";

const schema = z.object({
  name: z.string(),
  description: z.string(),
  start: z.date(),
  end: z.date(),
  budget: z.number(),
});

type FormSave = z.infer<typeof schema>;

interface UserData {
  id: string;
}

export function FromSave(user: UserData) {
  const {
    register,
    handleSubmit,
  } = useForm<FormSave>({
    mode: "all",
    reValidateMode: "onSubmit",
    resolver: zodResolver(schema),
  });

  function handleSave(data: FormSave) {
    serviceSave({...data, user_id: user.id, id: ""})
    .then(() => console.log("save success"))
    .catch((e) => {
      console.log(e.message);
    })
  }

  return (
    <>
      <Container className="container_project_save_form">
        <Row>
          <Form onSubmit={handleSubmit(handleSave)}>
            <Form.Group className="mb-3">
              <Form.Label>Name:</Form.Label>
              <Form.Control
                {...register("name")}
                type="text"
                placeholder="Enter name" 
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Description:</Form.Label>
              <Form.Control
                { ...register("description") }
                as="textarea"
                placeholder="Enter description"
                style={{ height: "100px" }}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Start:</Form.Label>
              <Form.Control
                { ...register("start", { valueAsDate: true }) } 
                type="date"
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>End:</Form.Label>
              <Form.Control
                { ...register("end", { valueAsDate: true }) }
                type="date"
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Budget:</Form.Label>
              <Form.Control
                { ...register("budget", { valueAsNumber: true }) }
                type="text"
                placeholder="Enter budget"
                onChange={(e) => {
                  let value = e.target.value;
                  value = value.replace(/(\D)/g, "");
                  value = value.replace(/(\d+)(\d{2})$/, "$1.$2");
                  e.target.value = value;
                }}
              />
            </Form.Group>
            <div className="d-grid gap-2">
              <Button variant="primary" type="submit" size="lg">
                Save
              </Button>
            </div>
          </Form>
        </Row>
      </Container>
    </>
  );
}