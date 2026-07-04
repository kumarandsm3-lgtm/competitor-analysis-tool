import { useEffect, useState } from "react";
import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api/analysis";

function App() {
  const [formData, setFormData] = useState({
    appOne: "Swiggy",
    appTwo: "Zomato",
    industry: "Food Delivery",
  });

  const [report, setReport] = useState(null);
  const [reports, setReports] = useState([]);
  const [keyword, setKeyword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchAllReports();
  }, []);

  const handleChange = (event) => {
    const { name, value } = event.target;

    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const generateAnalysis = async (event) => {
    event.preventDefault();

    setLoading(true);
    setMessage("");
    setReport(null);

    try {
      const response = await axios.post(`${API_BASE_URL}/generate`, formData);
      setReport(response.data);
      setMessage("AI report generated successfully ✨");
      fetchAllReports();
    } catch (error) {
      console.log("Full error:", error);

      if (error.response && error.response.data) {
        setMessage(JSON.stringify(error.response.data));
      } else if (error.request) {
        setMessage("Backend not reachable or CORS blocked");
      } else {
        setMessage("Something went wrong while generating report");
      }
    } finally {
      setLoading(false);
    }
  };

  const fetchAllReports = async () => {
    try {
      const response = await axios.get(API_BASE_URL);
      setReports(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const searchReports = async () => {
    if (!keyword.trim()) {
      fetchAllReports();
      return;
    }

    try {
      const response = await axios.get(`${API_BASE_URL}/search`, {
        params: {
          keyword: keyword,
        },
      });

      setReports(response.data);
    } catch (error) {
      console.error(error);
      setMessage("Search failed");
    }
  };

  const fetchLatestReports = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/latest`);
      setReports(response.data);
      setMessage("Showing latest reports");
    } catch (error) {
      console.error(error);
      setMessage("Failed to fetch latest reports");
    }
  };

  const deleteReport = async (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this report?");

    if (!confirmDelete) {
      return;
    }

    try {
      await axios.delete(`${API_BASE_URL}/${id}`);

      if (report && report.reportId === id) {
        setReport(null);
      }

      setMessage("Report deleted successfully");
      fetchAllReports();
    } catch (error) {
      console.error(error);
      setMessage("Delete failed");
    }
  };

  return (
    <div className="page">
      <div className="hero-section">
        <div className="hero-glow hero-glow-one"></div>
        <div className="hero-glow hero-glow-two"></div>

        <div className="container py-5 position-relative">
          <nav className="top-nav mb-5">
            <div className="brand">
              <div className="brand-icon">AI</div>
              <span>CompetitorIQ</span>
            </div>

            <div className="nav-badge">Spring Boot + React + Gemini</div>
          </nav>

          <div className="row align-items-center g-4">
            <div className="col-lg-7">
              <div className="hero-chip">AI Powered Product Strategy Tool</div>

              <h1 className="hero-title">
                Compare competitors and find product gaps in seconds.
              </h1>

              <p className="hero-subtitle">
                Generate strategic competitor analysis reports for apps like Swiggy vs Zomato
                using Spring Boot, MySQL, React and Gemini AI.
              </p>

              <div className="hero-stats">
                <div>
                  <h3>{reports.length}</h3>
                  <p>Saved Reports</p>
                </div>
                <div>
                  <h3>AI</h3>
                  <p>Generated Insights</p>
                </div>
                <div>
                  <h3>CRUD</h3>
                  <p>Full Stack APIs</p>
                </div>
              </div>
            </div>

            <div className="col-lg-5">
              <div className="glass-card hero-card">
                <div className="hero-card-header">
                  <span>Case Study</span>
                  <span className="live-dot">Live</span>
                </div>

                <div className="compare-box">
                  <div>
                    <small>App One</small>
                    <h4>{formData.appOne || "Swiggy"}</h4>
                  </div>

                  <div className="vs-circle">VS</div>

                  <div>
                    <small>App Two</small>
                    <h4>{formData.appTwo || "Zomato"}</h4>
                  </div>
                </div>

                <p className="mini-text">
                  AI compares features, UX, business model, target users, gaps and product opportunities.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <main className="container main-content">
        <div className="glass-card form-card">
          <div className="section-header">
            <div>
              <p className="eyebrow">Generate Analysis</p>
              <h2>Enter competitor details</h2>
            </div>
            <span className="section-pill">Gemini 2.5 Flash</span>
          </div>

          <form onSubmit={generateAnalysis}>
            <div className="row g-3">
              <div className="col-md-4">
                <label className="form-label">App One</label>
                <input
                  type="text"
                  name="appOne"
                  className="form-control custom-input"
                  value={formData.appOne}
                  onChange={handleChange}
                  placeholder="Example: Swiggy"
                />
              </div>

              <div className="col-md-4">
                <label className="form-label">App Two</label>
                <input
                  type="text"
                  name="appTwo"
                  className="form-control custom-input"
                  value={formData.appTwo}
                  onChange={handleChange}
                  placeholder="Example: Zomato"
                />
              </div>

              <div className="col-md-4">
                <label className="form-label">Industry</label>
                <input
                  type="text"
                  name="industry"
                  className="form-control custom-input"
                  value={formData.industry}
                  onChange={handleChange}
                  placeholder="Example: Food Delivery"
                />
              </div>
            </div>

            <button className="primary-action mt-4" type="submit" disabled={loading}>
              {loading ? (
                <>
                  <span className="spinner"></span>
                  Generating AI Report...
                </>
              ) : (
                "Generate AI Report"
              )}
            </button>
          </form>

          {message && <div className="message-box mt-4">{message}</div>}
        </div>

        {report && <ReportCard report={report} onDelete={deleteReport} />}

        <div className="glass-card reports-card">
          <div className="section-header">
            <div>
              <p className="eyebrow">Report Library</p>
              <h2>Saved competitor reports</h2>
            </div>
            <span className="section-pill">{reports.length} Reports</span>
          </div>

          <div className="search-row">
            <input
              type="text"
              className="form-control custom-input"
              value={keyword}
              onChange={(event) => setKeyword(event.target.value)}
              placeholder="Search by app or industry, example: Swiggy"
            />

            <button className="outline-action" onClick={searchReports}>
              Search
            </button>

            <button className="dark-action" onClick={fetchLatestReports}>
              Latest
            </button>
          </div>

          {reports.length === 0 ? (
            <div className="empty-state">
              <h4>No reports found</h4>
              <p>Generate your first AI competitor analysis report.</p>
            </div>
          ) : (
            <div className="table-responsive modern-table-wrap">
              <table className="table modern-table align-middle">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>App One</th>
                    <th>App Two</th>
                    <th>Industry</th>
                    <th>Winner</th>
                    <th>Action</th>
                  </tr>
                </thead>

                <tbody>
                  {reports.map((item) => (
                    <tr key={item.reportId}>
                      <td>#{item.reportId}</td>
                      <td>{item.appOne}</td>
                      <td>{item.appTwo}</td>
                      <td>
                        <span className="industry-badge">{item.industry}</span>
                      </td>
                      <td>
                        <span className="winner-badge">{item.winner}</span>
                      </td>
                      <td>
                        <button
                          className="view-btn me-2"
                          onClick={() => setReport(item)}
                        >
                          View
                        </button>

                        <button
                          className="delete-btn"
                          onClick={() => deleteReport(item.reportId)}
                        >
                          Delete
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </main>
    </div>
  );
}

function ReportCard({ report, onDelete }) {
  return (
    <div className="glass-card report-card">
      <div className="report-top">
        <div>
          <p className="eyebrow">AI Strategic Report</p>
          <h2>
            {report.appOne} <span>vs</span> {report.appTwo}
          </h2>
        </div>

        <button className="delete-report-btn" onClick={() => onDelete(report.reportId)}>
          Delete Report
        </button>
      </div>

      <div className="report-meta">
        <span>{report.industry}</span>
        <span>Winner: {report.winner}</span>
        <span>Report #{report.reportId}</span>
      </div>

      <div className="insight-grid">
        <InsightCard title="Core Features" content={report.coreFeatures} />
        <InsightCard title="UX and Design" content={report.uxDesign} />
        <InsightCard title="Target User" content={report.targetUser} />
        <InsightCard title="Business Model" content={report.businessModel} />
        <InsightCard title="Gaps" content={report.gaps} />
        <InsightCard title="Opportunity" content={report.opportunity} />
      </div>

      <div className="recommendation-box">
        <h4>Strategic Recommendation</h4>
        <p>{report.strategicRecommendation}</p>
      </div>
    </div>
  );
}

function InsightCard({ title, content }) {
  return (
    <div className="insight-card">
      <h4>{title}</h4>
      <p>{content}</p>
    </div>
  );
}

export default App;