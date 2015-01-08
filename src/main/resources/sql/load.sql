INSERT INTO User VALUES (1, CURTIME(), "test@mail.xyz", CURTIME(), "TestUser", "angular", CURTIME());	

INSERT INTO GraphOfContent VALUES (1);
INSERT INTO Lecture VALUES (1, CURTIME(), CURTIME(), "TowelsRule", "Prof. Dr. Washcloth", 100, "Bathroom University", 1, 1);


INSERT INTO FileObject VALUES (1, CURTIME(), CURTIME(), "File 1", NULL, 1, "F1", 1);
INSERT INTO FileObject VALUES (2, CURTIME(), CURTIME(), "File 1", NULL, 1, "F1", 1);
INSERT INTO FileObject VALUES (3, CURTIME(), CURTIME(), "File 1", NULL, 1, "F1", 1);

INSERT INTO Node VALUES (1, 1, "Chapter 1", 1, 1);
INSERT INTO Node VALUES (2, 1, "Chapter 2", 2, 1);
INSERT INTO Node VALUES (3, 2, "Section 2.1", 3, 1);

INSERT INTO GraphOfContent_Node VALUES (1, 1);
INSERT INTO GraphOfContent_Node VALUES (1, 2);
INSERT INTO GraphOfContent_Node VALUES (1, 3);

INSERT INTO DirectedEdge VALUES (1, 1, 2);
INSERT INTO DirectedEdge VALUES (2, 2, 3);

INSERT INTO GraphOfContent_DirectedEdge VALUES (1, 1);
INSERT INTO GraphOfContent_DirectedEdge VALUES (1, 2);