from http.server import HTTPServer, Base HTTPRequestHandler


class SimpleCloudApp(Base HTTPRequestHandler):
def do_GET(self):
self.send_response(200)
self.send_header('Content-type','text/html')
self.end_headers()
self.wfile.write(
b"<h1>Cloud Computing Practical: "
b"Docker Service Running Successfully!</h1>" )
if __name__ == "__main__":
server = HTTPServer( ('0.0.0.0', 8080),SimpleCloudApp )
print("[*] Containerized service "
"listening on port 8080...")
server.serve_forever()