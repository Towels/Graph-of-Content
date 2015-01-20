INSERT INTO Token VALUES ("a7a263c0-c977-407f-a2eb-7e728cafd264", NOW());

INSERT INTO User VALUES (1, NOW(), "test@mail.xyz", NOW(), "TestUser", "angular", NOW(), "a7a263c0-c977-407f-a2eb-7e728cafd264");	

INSERT INTO GraphOfContent VALUES (1);
INSERT INTO Lecture VALUES (1, NOW(), NOW(), "TowelsRule", "Prof. Dr. Washcloth", 100, "Bathroom University", 1, 1);

INSERT INTO FileObject VALUES (1, NOW(), NOW(), "File 1", NULL, "BIN", "F1", 1);
INSERT INTO FileObject VALUES (2, NOW(), NOW(), "File 1", NULL, "BIN", "F1", 1);
INSERT INTO FileObject VALUES (3, NOW(), NOW(), "File 1", NULL, "BIN", "F1", 1);

INSERT INTO Node VALUES (1, "CHAPTER", "Chapter 1", 1, 1);
INSERT INTO Node VALUES (2, "CHAPTER", "Chapter 2", 2, 1);
INSERT INTO Node VALUES (3, "SECTION", "Section 2.1", 3, 1);

INSERT INTO GraphOfContent_Node VALUES (1, 1);
INSERT INTO GraphOfContent_Node VALUES (1, 2);
INSERT INTO GraphOfContent_Node VALUES (1, 3);

INSERT INTO DirectedEdge VALUES (1, 1, 2);
INSERT INTO DirectedEdge VALUES (2, 2, 3);

INSERT INTO GraphOfContent_DirectedEdge VALUES (1, 1);
INSERT INTO GraphOfContent_DirectedEdge VALUES (1, 2);