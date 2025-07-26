import React, { useState, useEffect } from 'react';

function ArtViewer() {
  const [art, setArt] = useState(null);
  const [error, setError] = useState('');

  const fetchArtwork = async (endpoint = '/api/artwork') => {
    try {
      const res = await fetch(`http://localhost:8080${endpoint}`);
      if (!res.ok) throw new Error('Failed to fetch');
      const data = await res.json();
      setArt(data);
      setError('');
    } catch (err) {
      setError('Oops! Could not load artwork.');
      setArt(null);
    }
  };

  useEffect(() => {
    fetchArtwork(); // Load first artwork on page load
  }, []);

  return (
    <div id="art-gallery-container" style={{ textAlign: 'center', fontFamily: 'sans-serif' }}>
      <h1 id="gallery-title">🌸 Art Gallery 🌸</h1>

      {error && <p id="error-message">{error}</p>}

      {art ? (
        <div id="artwork-display">
          <h2 id="art-title">{art.title}</h2>
          <h3 id="art-artist">{art.artist}</h3>
          <img id="art-image" src={art.imgUrl} alt={art.title} style={{ maxWidth: '500px' }} />
        </div>
      ) : (
        <p>Loading artwork...</p>
      )}

      <div id="navigation-buttons" style={{ marginTop: '20px' }}>
        <button id="prev-button" onClick={() => fetchArtwork('/api/artwork/previous')}>← Previous</button>
        <button id="next-button" onClick={() => fetchArtwork('/api/artwork/next')} style={{ marginLeft: '10px' }}>
          Next →
        </button>
      </div>
    </div>
  );
}

export default ArtViewer;
