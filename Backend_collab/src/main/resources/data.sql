-- Insert sample data into the 'user' table
INSERT INTO `user` (`email`, `first_name`, `last_name`, `password`, `username`)
VALUES
  ('user1@example.com', 'John', 'Doe', 'password123', 'johndoe'),
  ('user2@example.com', 'Jane', 'Smith', 'securepass', 'janesmith'),
  ('user3@example.com', 'Bob', 'Johnson', 'pass1234', 'bobjohnson');

-- Insert sample data into the 'dashboard' table
INSERT INTO `dashboard` (`name`)
VALUES
  ('Project Dashboard'),
  ('Team Dashboard');

-- Insert sample data into the 'dashboard_members' table
INSERT INTO `dashboard_members` (`dashboard_id`, `members_id`)
VALUES
  (1, 1), -- John Doe is a member of Project Dashboard
  (1, 2), -- Jane Smith is a member of Project Dashboard
  (2, 2), -- Jane Smith is a member of Team Dashboard
  (2, 3); -- Bob Johnson is a member of Team Dashboard

-- Insert sample data into the 'task' table
INSERT INTO `task` (`date_creation`, `date_limit`, `description`, `status`, `title`, `dashboard_id`, `user_id`)
VALUES
  ('2023-01-01', '2023-01-15', 'Task 1 Description', 'In Progress', 'Task 1', 1, 1),
  ('2023-02-01', '2023-02-28', 'Task 2 Description', 'Pending', 'Task 2', 1, 2),
  ('2023-03-01', '2023-03-15', 'Task 3 Description', 'Completed', 'Task 3', 2, 1);

-- Insert sample data into the 'sub_task' table
INSERT INTO `sub_task` (`sub_title`, `task_id`)
VALUES
  ('Subtask A', 1),
  ('Subtask C', 2),
  ('Subtask D', 3);

-- Insert sample data into the 'comment' table
INSERT INTO `comment` (`comment`, `task_id`, `user_id`)
VALUES
  ('Great progress on Task 1!', 1, 1),
  ('Need clarification on Task 2', 2, 2),
  ('Completed Subtask A', 1, 2),
  ('Task 3 is finished!', 3, 3);
