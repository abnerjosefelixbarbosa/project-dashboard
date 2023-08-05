import { zodResolver } from "@hookform/resolvers/zod";
import { useState } from "react";
import { Button, Form, Modal } from "react-bootstrap";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { edit as serviceEdit } from "../../../service/serviceProject";

const schema = z.object({
  name: z.string().min(1, "min 1 character").max(100, "max 100 characters"),
  description: z
    .string()
    .min(1, "min 1 character")
    .max(200, "max 200 character"),
  start: z.date().min(new Date(), "min future date"),
  end: z.date().min(new Date(), "min future date"),
  budget: z.number(),
});

type FormEdit = z.infer<typeof schema>;

interface ModalData {
    id: string;
    name: string;
    description: string;
    start: Date;
    end: Date;
    budget: number;
}

export function ModalEdit({
  id,
  name,
  description,
  start,
  end,
  budget,
}: ModalData) {
  const [show, setShow] = useState<boolean>(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormEdit>({
    mode: "all",
    reValidateMode: "onSubmit",
    resolver: zodResolver(schema),
  });

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  function handleEdit(data: FormEdit) {
    serviceEdit({ ...data, id: id, user_id: "" })
      .then(() => {
        console.log("project edited")
      })
      .catch((e) => console.log(e.message));
  }

  return (
    <>
      <Button variant="primary" onClick={handleShow}>
        Edit
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Edit</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit(handleEdit)}>
            <Form.Group className="mb-3">
              <Form.Label>Name:</Form.Label>
              <Form.Control
                {...register("name", { value: name })}
                type="text"
                placeholder="Enter name"
              />
              <Form.Text className="text_color">
                {errors.name?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Description:</Form.Label>
              <Form.Control
                {...register("description", { value: description })}
                as="textarea"
                placeholder="Enter description"
                style={{ height: "100px" }}
                defaultValue={description}
              />
              <Form.Text className="text_color">
                {errors.description?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Start:</Form.Label>
              <Form.Control
                {...register("start", { valueAsDate: true })}
                type="date"
              />
              <Form.Text className="text_color">
                {errors.start?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>End:</Form.Label>
              <Form.Control
                {...register("end", { valueAsDate: true })}
                type="date"
              />
              <Form.Text className="text_color">
                {errors.end?.message}
              </Form.Text>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Budget:</Form.Label>
              <Form.Control
                {...register("budget", { valueAsNumber: true, value: budget })}
                type="text"
                placeholder="Enter budget"
                onChange={(e) => {
                  let value = e.target.value;
                  value = value.replace(/(\D)/g, "");
                  value = value.replace(/(\d+)(\d{2})$/, "$1.$2");
                  e.target.value = value;
                }}
              />
              <Form.Text className="text_color">
                {errors.budget?.message}
              </Form.Text>
            </Form.Group>
            <div className="d-grid gap-2">
              <Button variant="primary" type="submit" size="lg">
                Edit
              </Button>
            </div>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" size="lg" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}
